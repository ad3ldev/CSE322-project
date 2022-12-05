const PatientDashboard = ({ patient }) => {
	let isFemale = patient.gender === "F" ? true : false;
	return (
		<div className='dash'>
			<div className='dash-name'>
				{isFemale && <h1>Hello Mrs. {patient.name}</h1>}
				{!isFemale && <h1>Hello Mr. {patient.name}</h1>}
			</div>
		</div>
	);
};

export default PatientDashboard;
